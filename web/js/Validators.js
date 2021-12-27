function Validator() {
    let from = document.getElementById('from');
    let to = document.getElementById('to');
    let errorElement = document.getElementById('price-error');
    from.oninput = function () {
        errorElement.innerText = '';
    };
    to.oninput = function () {
        errorElement.innerText = '';
    };
    from.onblur = function () {
        var message = isNumber(from.value);
        if (message) {
            errorElement.innerText = message;
        } else {
            errorElement.innerText = '';
        }
    };
    to.onblur = function () {
        var message = isNumber(to.value);
        if (message) {
            errorElement.innerText = message;
        } else {
            message = formatPrice(from.value, to.value);
            if (message) {
                console.log(to.value);
                errorElement.innerText = message;
            } else {
                errorElement.innerText = '';
            }
        }
    };
    var formElement = document.getElementById('form-1');
    if (formElement) {
        formElement.onsubmit = function (event) {
            event.preventDefault();

            if (isNumber(from.value) || isNumber(to.value) || formatPrice(from.value, to.value)) {
                alert('Vui lòng nhập đúng form tìm kiếm');
            } else {
                formElement.submit();
            }

        };
    }
}

function ValidatorChangePassword() {

    let formChangePassword = document.getElementById('form-changepassword');
    let password = document.getElementById('password');
    let password_confirmation = document.getElementById('password_confirmation');
    //password onblur
    password.onblur = function () {
        var errorElement = password.parentElement.querySelector('.form-message');
        var message = isPassword(password.value);
        console.log(message);
        if (message) {
            password.parentElement.classList.add('invalid');
            errorElement.innerText = message;
        } else {
            password.parentElement.classList.remove('invalid');
            errorElement.innerText = "";
        }
    }
    password.oninput = function () {
        var errorElement = password.parentElement.querySelector('.form-message');
        password.parentElement.classList.remove('invalid');
        errorElement.innerText = "";
    }
    //confirm password
    password_confirmation.onblur = function () {
        var errorElement = password_confirmation.parentElement.querySelector('.form-message');
        var message = isConfirm(password.value, password_confirmation.value);
        if (message) {
            password_confirmation.parentElement.classList.add('invalid');
            errorElement.innerText = message;
        } else {
            password_confirmation.parentElement.classList.remove('invalid');
            errorElement.innerText = "";
        }
    }
    password_confirmation.oninput = function () {
        var errorElement = password_confirmation.parentElement.querySelector('.form-message');
        password_confirmation.parentElement.classList.remove('invalid');
        errorElement.innerText = "";
    }
    // submit validate
    formChangePassword.onsubmit = function (e) {
        e.preventDefault();
        if (isPassword(password.value) || isConfirm(password.value, password_confirmation.value)) {
            alert('Vui lòng nhập đúng form đổi mật khẩu');
        } else {
            formChangePassword.submit();
        }
    };
}
function ValidatorRegister() {
    let form = document.getElementById('form-register');
    let user = document.getElementById('newUsername');
    let email = document.getElementById('email');

    user.onblur = function () {
        var error = document.getElementById('newUsername');
        var message = isRequired(user.value);
        if (message)
            error.innerText = message;
        else{
               error.innerText = '';
        }
    };
    email.onblur = function () {
        var error = document.getElementById('error-massage');
        var message = isEmail(email.value);
        
        if (message)
            error.innerText = message;
        else{
               error.innerText = '';
        }
    };
    email.oninput = function () {
        var error = document.getElementById('error-massage');
        error.innerText = "";
    };
    form.onsubmit = function (e) {
        let checkusername = document.getElementById('checkUsername');
        e.preventDefault();
        if (isRequired(user.value) || isEmail(email.value) || checkUsername(checkusername.value)) {
            alert('please input correct format !');
        } else {
            form.submit();
        }
    };
}
// confirm pasword
function isPassword(value) {
    var regex = /^(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
    return regex.test(value) ? undefined : 'Mật khẩu phải chứa ít nhất 1 chữ in hoa và độ dài tối thiểu là 8 kí tự';
}
function isConfirm(value, getpassword) {
    return (value === getpassword) ? undefined : 'Mật khẩu nhập vào không trùng khớp ';
}
//validate number
function isNumber(value) {
    if (value === '')
        return undefined;
    var regex = /^[0-9]*$/;
    return regex.test(value) ? undefined : 'Vui lòng nhập giá sản phẩm là các chữ số';
}
function isRequired(value) {
    return value.trim() ? undefined : 'please input this field !';
}
function checkUsername(value) {
    if (value === '')
        return true;
    return false;
}
function isEmail(value) {
    var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return regex.test(value) ? undefined : 'Email does not exists !';
}
function formatPrice(from, to) {
    if (((from === '' || from === 0) && to === '') || to === '')
        return undefined;
    return (from - to) < 0 ? undefined : 'Vui lòng điền khoảng giá phù hợp';
}

//show hide password
var pwHide = false;
function hid(selector) {
    if (pwHide) {
        document.getElementById(selector).setAttribute('type', 'password');
        pwHide = false;
    } else {
        document.getElementById(selector).type = 'text';
        pwHide = true;
    }
}