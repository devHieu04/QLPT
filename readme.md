# LOGIN

### Action Login
> Lấy thông tin từ người dùng bao gồm username và password, sau đó gửi cho controller để xử lý. Controller sẽ gọi đến model để kiểm tra thông tin đăng nhập. Nếu thông tin đúng thì sẽ trả về cho controller thông tin của người dùng, controller sẽ tìm kiếm trên database dựa theo thông tin mà người dùng cung cấp và trả về 2 giá trị là role và email.

- Chúng ta cần role để kiểm tra quyền truy cập là admin hay user
- Chúng ta cần email vì lát nữa sẽ sử dụng email để lấy thông tin người dùng (sử dụng email trong bảng account lm foreign key cho bảng tenant)

``mysql
ALTER TABLE tenant
ADD FOREIGN KEY (email)
REFERENCES account(email);
``

### Action Register
> Khi nhấn vào nút register, tạo đối tượng register và show nó lên bằng hàm showView() đồng thời tắt Frame Login bằng this.dispose();


# REGISTER

### Action Register
> Lấy thông tin từ người dùng bao gồm username, password, email và xử lý try/catch kiểm tra tính đúng của các thông tin như không được để trống thông tin, điều kiện của password,... nếu tất cả đều đúng thì sẽ gửi cho controller để xử lý. Controller sẽ truy vấn database để lưu thông tin người dùng vào bảng account và trả về cho view thông báo đăng ký thành công.

> Sau khi đăng ký thành công thì trở về lại form login

### Action Login
> Tương tự như action login ở trên


# AccountView
>`pnRight.removeAll();` Xóa tất cả các thành phần con hiện tại từ container pnRight. Điều này làm sạch giao diện hiện tại của pnRight để chuẩn bị cho việc thêm các thành phần mới.

> `pnRight.add(pnRegister);` Thêm panel pnRegister vào container pnRight
 
> `pnRight.validate();` Cập nhật lại giao diện của pnRight

> `pnRight.repaint();` Vẽ lại giao diện của pnRight

# TenantView

## Về giao diện
> Bố cục chia làm 2 phần top và bottom. trong mỗi phần cx chia ra 2 phần top và bottom nữa.
> Ở phần nội dung (phần bottom của mỗi phần) chia theo layout grid 2 cột 1 dòng.

## Về chức năng
> TenantView chỉ có chức năng cập nhật thông tin của người dùng. và chỉ cập nhật các thông tin cá nhân như căn cước, họ tên, ngày sinh, email. Các thông tin khác là thông tin do admin nắm quyền nên không được sửa.

### Cách thức hoạt động
> Hàm `input()` sẽ lấy các thông tin người dùng để lưu vào biến, lát nữa sẽ tổng hợp thành 1 object.

> Gọi hàm `updateTenant()` để gửi thông tin người dùng lên controller để xử lý. Controller sẽ gọi đến model để truy vấn database và cập nhật thông tin người dùng.

> Sau khi cập nhật thì set lại các giá trị cho các lable bằng hàm `setValue()`. 