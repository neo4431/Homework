# Paging and sorting với spring boot và thymleaf
1. Tạo project với dependency: JPA, H2, Thymleaf, lombok,.... 
2. Sử dụng mockaroo để tạo file sql với 100 records
![sql](./photos/sql.png)
3. Tạo entity Product mapping với dữ liệu đã tạo
![entity](./photos/entity.png)
4. Tạo ProductRepository extend JpaRepository
![repo](./photos/repo.png)
5. Tạo service sử dụng pagable để phân trang và sắp xếp theo các field với "order" là thứ tự sắp xếp (asc or desc)
![service](./photos/service.png)
![service](./photos/service2.png)
6. Tạo Controller
![controller1](./photos/controller1.png)
![controller2](./photos/controller2.png)
7. Tạo file 2 file html lần lượt là màn hình danh sách product và màn hình thêm/ sửa product
![index](./photos/html1.png)
![add/edit](./photos/html2.png)
8. Sử dụng js để bắt sự kiện click reload lại trang với url tương ứng
- Hàm chạy ngay web dược khởi tạo
![invoke](./photos/functioninvoke.png)
- Hàm xử lý di chuyển page
![page](./photos/paging.png)
- Hàm xử lý sắp xếp khi click vào tên các trường trên màn hình danh sách product
![page](./photos/sorting.png)
