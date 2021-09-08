- Tạo project với dependencies: Jpa, Lombok, H2 database
![dependencies](./photo/dependencies.png)
- Tạo file sql với 20 bản ghi bằng Mockaroo.com và cho vào resource
- Bổ sung cấu hình vào application.properties
![file properties](./photo/properties.png)
- Tạo entity với các trường: firstName,lastName, email, mobile, job.
![entity](./photo/entity.png)
- Tạo file CustomerRepository extend CrudRepository
![file properties](./photo/repository.png)
- Tạo interface service:
![service interface](./photo/interface.png)
- Tạo service có các method theo yêu cầu đề bài :
    + findAll(), findByKeyword(String keyword), orderByEmail(), orderByFirstName,...
![service](./photo/service1.png)
![service](./photo/service2.png)
- Tại file Application: autowired service, sử dụng Logger để in ra terminal 
![app](./photo/application.png)
- Màn hình terminal sau khi gọi hàm sắp xếp theo email (các trường khác tương tự)
![terminal](./photo/orderbyemail.png)
- Màn hình terminal sau khi gọi hàm findByKeyword:
![terminal](./photo/findbykeyword.png)
- Thực hiện các truy vấn , sắp xếp trên h2-console:
![h2-console](./photo/SELECT.png)
![h2-console](./photo/ORDER.png)