package com.example.thymeleaf.service;

import com.example.thymeleaf.entity.Customer;
import com.example.thymeleaf.entity.CustomerDTO;
import com.example.thymeleaf.entity.Review;
import com.example.thymeleaf.entity.ReviewDTO;
import com.example.thymeleaf.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 회원등록
    @Transactional
    public Customer register(Customer customer){
         return customerRepository.save(customer); // save() -> insert SQL
    }
   // 회원목록 가져오기( + 리뷰:List<Review>, + 장바구니:List<Cart>
   @Transactional(readOnly = true)
    public List<Customer> getAllCustomer(){
       //return customerRepository.findAll(); // select SQL~
       //List<Customer> coustomers=customerRepository.findAll();
       //coustomers.forEach(customer -> Hibernate.initialize(customer.getReviews()));
      return customerRepository.findAllWithReviews();
   }

   // 특정회원 한명의 정보 가져오기
   @Transactional(readOnly = true)
    public Optional<Customer> getById(Long id){ // PK(id)
        return customerRepository.findById(id);
        // select * from Customer where idx=
    }

    // 특정회원의 정보를 수정하기
    @Transactional
    public void cusUpdate(Long id, Customer customer){
           Optional<Customer> optional=customerRepository.findById(id);
           Customer dbCustomer;
           if(optional.isPresent()){
               dbCustomer=optional.get();
           }else{
               throw new IllegalArgumentException("error");
           }
        dbCustomer.setUsername(customer.getUsername()); // 아이디
        dbCustomer.setAge(customer.getAge()); // 나이
        customerRepository.save(dbCustomer); // update SQL
    }

    // 회원삭제
    @Transactional
    public void cusDelete(Long id){ // id
        customerRepository.deleteById(id); // delete ~ where id=id
    }

    // 아이디와 패스워드가 일치하는 회원을 가져오기
    @Transactional(readOnly = true)
    public  Customer login(String username, String password){
          // Optional<Customer> optional=customerRepository.findByUsernameAndPassword(username,password);
          Optional<Customer> optional=customerRepository.usernameAndPassword(username,password);
           Customer customer;
           if(optional.isPresent()){
               customer=optional.get();
           }else{
               throw new IllegalArgumentException("error");
           }
           return customer;
    }

    @Transactional(readOnly = true)
    public List<Customer> getAge(int age){
          //return customerRepository.findByAgeGreaterThanEqual(age);
          return customerRepository.ageGreaterThanEqual(age);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomerWithReviews() {
        List<Customer> customers = customerRepository.findAllWithReviews();
        return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //Customer -> CustomerDTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setReviews(customer.getReviews().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return customerDTO;
    }

    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewDTO.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setCost(review.getCost());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }
}
