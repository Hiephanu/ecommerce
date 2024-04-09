package com.example.ecommerce.product.service;

import com.example.ecommerce.category.model.dto.CategoryDto;
import com.example.ecommerce.category.model.entity.Category;
import com.example.ecommerce.category.repository.CategoryRepository;
import com.example.ecommerce.category.service.CategoryCrudService;
import com.example.ecommerce.product.model.dto.ProductDetailDto;
import com.example.ecommerce.product.model.dto.ProductDto;
import com.example.ecommerce.product.model.dto.ProductFilterRequest;
import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.repository.ProductRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetProductService {
    private final ProductRepository productRepository;
    private final CategoryCrudService categoryCrudService;
    private  final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    public Result<ProductDetailDto, DomainException> getProductById(String id){
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if(product.isPresent()){
            Page<Category> categories = categoryRepository.findByProductsId(product.get().getId(),PageRequest.of(0,10));
            ProductDetailDto productDetailDto = ProductDetailDto.builder()
                    .id(product.get().getId())
                    .title(product.get().getTitle())
                    .image(product.get().getImage())
                    .summary(product.get().getSummary())
                    .description(product.get().getDescription())
                    .price(product.get().getPrice())
                    .quantity(product.get().getQuantity())
                    .origin(product.get().getOrigin())
                    .categories(categories.getContent())
                    .shopId(product.get().getShop().getId().toString())
                    .build();
            return Result.success(productDetailDto);
        } else {
            return Result.fail(new DomainException("Not_Found","Not found"));
        }
    }
    public Result<List<ProductDto>,DomainException> searchProductByName(String keyword,int page,int size){
        try{
            PageRequest pageRequest = PageRequest.of(page,size);
            Page<Product> productPage = productRepository.findByTitleContaining(keyword,pageRequest);
            List<ProductDto> productDtos = productPage.getContent().stream().map(product -> {
                Page<Category> categories = categoryRepository.findByProductsId(product.getId(), PageRequest.of(0,10));
                return ProductDto.convertToProductDto(product, categories.getContent());
            }).toList();
            return Result.success(productDtos);
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("Fail to get product","FAIL"));
        }
    }
    public Result<List<ProductDto>, DomainException> getProductByCategory(String category){
        Result<CategoryDto,DomainException> categoryDto = categoryCrudService.getCategoryByName(category);
        try {

        } catch (Exception e){

        }
        return null;
    }

    public Result<List<ProductDto>, DomainException> filterProduct(ProductFilterRequest productFilterRequest,int page,int size){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);

            Predicate price = criteriaBuilder.between(root.get("price"),productFilterRequest.getMinPrice(),productFilterRequest.getMaxPrice());
            Predicate origin = criteriaBuilder.equal(root.get("origin"),productFilterRequest.getOrigin());
            Predicate quantity = criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"),productFilterRequest.getQuantity());

            Predicate finalFilter = criteriaBuilder.and(price,origin,quantity);

            criteriaQuery.where(finalFilter);

            TypedQuery<Product> productTypedQuery = entityManager.createQuery(criteriaQuery);
            productTypedQuery.setFirstResult(page*size);
            productTypedQuery.setMaxResults(size);
            List<Product> products = productTypedQuery.getResultList();
            List<ProductDto> productDtos = products.stream().map(product -> {
                Page<Category> categories = categoryRepository.findByProductsId(product.getId(), PageRequest.of(0,10));
                return ProductDto.convertToProductDto(product, categories.getContent());
            }).toList();
            return  Result.success(productDtos);
        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(new DomainException("FAIL","fail"));
        }
    }

}
