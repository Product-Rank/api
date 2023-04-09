package com.productrank.api.domain.service;

import com.productrank.api.domain.dto.CommentsDto;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.entity.Comments;
import com.productrank.api.domain.entity.Company;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.ProductRepository;
import com.productrank.api.domain.repository.UserRepository;
import com.productrank.api.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final CommentsService commentsService;
    private final UserRepository userRepository;
    public List<ProductDto> getAllProductList() {
        return productRepository.findAll().stream()
                .map(v -> ProductDto.from(v, v.getCompany().getCompanyName()))
                .collect(Collectors.toList());
    }

    public ProductDto getDetailsProduct(Long productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage()));
        return ProductDto.from(product);
    }


    public List<ProductDto> getPageProductList(int pageSize) {
        return null;
    }

    @Transactional
    public ProductDto makeProduct(ProductDto productDto, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        Product product = Product.builder()
                .productName(productDto.productName())
                .productDescription(productDto.productDescription())
                .ThumbnailUrl(productDto.ThumbnailUrl())
                .vote(0L)
                .company(company)
                .build();
        Product entity = productRepository.save(product);
        entity.setCompany(company);

        return ProductDto.from(entity);
    }

    @Transactional
    public ProductDto enrollComments(CommentsDto commentsDto, Long userId, Long productId){
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException(ErrorCode.NOT_EXIST_USER.getMessage()));
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage()));

        Comments comments = commentsService.enrollComments(commentsDto);

        comments.setUser(user);
        comments.setProduct(product);

        return ProductDto.from(product);
    }
    @Transactional
    public ProductDto updateProduct(User user, ProductDto dto) {
        Product product = productRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage()));

        if(user.getId() != product.getCompany().getOwner().getId()){
            throw new RuntimeException(ErrorCode.NOT_MATCH_WRITER.getMessage());
        }

        product.updateProductDescription(dto.productDescription());
        product.updateName(dto.productName());

        return ProductDto.from(product);
    }
    @Transactional
    public ProductDto voteUp(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage()));

        product.voteUp();

        return ProductDto.from(product);
    }

    public ProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorCode.NOT_EXIST_PRODUCT.getMessage()));

        productRepository.delete(product);
        return ProductDto.from(product);
    }
}
