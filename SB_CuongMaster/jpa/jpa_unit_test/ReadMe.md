# Data JPA Test
1. Tạo project spring boot với dependencies : JPA, H2, AssertJ, Junit

    ![dependencies](./photos/dependency.png)

2. Tạo data test "person.sql" và Entity "Person.java" với các trường tương ứng

    ![data](./photos/data.png)
    ![entity](./photos/person.png)

3. Thực hiện truy vấn và unit test
    - Đếm số người theo từng công việc theo thứ tự giảm dần:
        + Tạo class JobCount với các trường tương ứng với các cột được trả về từ câu truy vấn:

            ![JobCount](./photos/entity_query4.png)
        + Tạo interface PersonRepo extend JpaRepository và viết custom query:

            ![repo1](./photos/repo1.png)
        + Tạo file PersonTest.java trong package test để thực hiện unit test

            ![test](./photos/test1.png)
    - Đếm top 5 công việc có nhiều người làm nhất: (sử dụng pageable để lấy 5 bản ghi đầu tiên)

        ![repo2](./photos/repo2.png)
        ![test2](./photos/test2.png)
    - Đếm số lượng người nhóm theo từng công việc và cùng thành phố:
        + Tạo class JobAndCityCount.java: 

            ![JobAndCityCount](./photos/entity_query.png)
        + Custom query và test

            ![repo3](./photos/repo3.png)
            ![test3](./photos/test3.png)
    - Đếm mức lương trung bình theo từng công việc thứ tự giảm dần:
        + Tạo class JobAverageSalary.java

            ![JobAverageSalary](./photos/entity_query3.png)
        + Custom query và test

            ![repo4](./photos/repo4.png)
            ![test4](./photos/test4.png)
    - Đếm mức lương trung bình theo từng thành phố thứ tự giảm dần:
        + Tạo class CityAvgSalary.java

            ![CityAvgSalary](./photos/entity_query2.png)
        + Custom query và test

            ![repo5](./photos/repo5.png)
            ![test5](./photos/test5.png)
    - Đếm 5 công việc nhiều nhất theo từng thành phố:
        + Sử dụng đối tượng JobAndCityCount; 
        + Custom query và test
        
            ![repo6](./photos/repo6.png)
            ![test6](./photos/test6.png)
