package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.CategoryRequest;
import com.example.electronicsspringbootclientservice.CategoryResponse;
import com.example.electronicsspringbootclientservice.CategoryServiceGrpc;
import com.example.electronicsspringbootclientservice.model.Category;
import com.example.electronicsspringbootclientservice.repository.CategoryRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryGRPCServerImpl extends CategoryServiceGrpc.CategoryServiceImplBase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public void getCategory(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {
        logger.info("received signal from grpc client >>> processing request");
         int id = request.getId();
         Category category = categoryRepository.findById(id).orElseThrow(() ->  new RuntimeException("entity not found!"));
//         CategoryResponse response = CategoryResponse.newBuilder().setId(category.getId()).setDetail(category.getDetail()).setName(category.getName()).build();
         CategoryResponse response = modelMapper.map(category, CategoryResponse.class);
         responseObserver.onNext(response);
         responseObserver.onCompleted();
    }
}
