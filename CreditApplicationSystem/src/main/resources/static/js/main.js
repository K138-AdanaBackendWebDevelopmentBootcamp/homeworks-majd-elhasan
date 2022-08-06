$(document).ready(function (){
    const checkbox = document.querySelector('.modeSwitcher #lightSwitch');
    !localStorage.getItem('dark-mode')?localStorage.setItem('dark-mode','true'):localStorage.getItem('dark-mode');
    // let mode = localStorage.getItem('dark-mode')==='true'?true:false ; // is the same as let mode = localStorage.getItem('dark-mode')==='true'
    document.querySelector('.modeSwitcher #lightSwitch').checked =localStorage.getItem('dark-mode') === 'true';
    //console.log(localStorage.getItem('dark-mode'));
    function dark_mode() {
        localStorage.setItem('dark-mode','true');
        $("body").addClass('bg-dark').removeClass('light-mode');
        $('.modal-content').addClass('dark-mode').removeClass('light-mode');
        $('.modal-content input').addClass('dark-mode').removeClass('light-mode');
        $('.table-header').addClass('dark-mode').removeClass('light-mode');
        $('#lightSwitch_svg').attr('style','fill:white')
        $('.dynamic-table').addClass('table-dark').removeClass('light-mode');
        $('#page-header').addClass('text-light').removeClass('light-mode');
    }
    function light_mode(){
        localStorage.setItem('dark-mode','false');
        $("body").removeClass('bg-dark').addClass('light-mode');
        $('.modal-content').removeClass('dark-mode').addClass('light-mode');
        $('.modal-content input').removeClass('dark-mode').addClass('light-mode');
        $('.table-header').removeClass('dark-mode').addClass('light-mode');
        $('#lightSwitch_svg').attr('style','fill:black').addClass('light-mode');
        $('.dynamic-table').removeClass('table-dark').addClass('light-mode');
        $('#page-header').removeClass('text-light').addClass('light-mode');
    }
    if (checkbox.checked) dark_mode();
    else {light_mode();}

    // editing
    $(".table .edit-btn").on("click",function (event){
        $('#edit_Modal').modal('show');
        event.preventDefault();
        const href = $(this).attr("href");
        $.get(href,function(u){
            $('#identityNumberEdit').val(u.identityNumber);
            $('#fullNameEdit').val(u.fullName);
            $('#phoneNumberEdit').val(u.phoneNumber);
            $('#salaryEdit').val(u.salary);
            $('#databaseIdEdit').val(u.databaseId);
        })
    })

    // deleting
    $('.table #delete-btn').on('click',function (event){
        event.preventDefault();
        console.log(" // deleting");
        const href =$(this).attr('href');
        $('#delete_modal #confirm-del').attr('href',href);
        $('#delete_modal').modal('show');
    })

    // getting credit information
    $('.table #credit-info-btn').on('click',function (event){
        event.preventDefault();
        console.log(" // getting credit information");
        const href =$(this).attr('href');
        $.get(href,function(u){
            if(!u.credit_info)  // if the user HAS NOT applied for a credit before ,run this couple of lines of code
            {
                $('#askCredit_modal #askCredit').attr('href',"/users/ApplyForCredit/?idCardNumber="+u.identityNumber);
                $('#askCredit_modal').modal('show');
            }
            else if(u.credit_info) {  // if the user HAS applied for a credit before ,run this couple of lines of code
                $('#credit_state').html(u.credit_info.state);
                if(u.credit_info.state==="success")
                {
                    console.log('success...');
                    if (document.querySelector('#Credit_modal .modal-body').classList.contains('visually-hidden'))
                        $('#Credit_modal .modal-body').removeClass('visually-hidden');
                    $('#creditLimit').text(u.credit_info.creditLimit);
                }
                else{
                    console.log('failure...');
                    if (!document.querySelector('#Credit_modal .modal-body').classList.contains('visually-hidden'))
                        $('#Credit_modal .modal-body').addClass('visually-hidden');
                }

                $('#Credit_modal').modal('show');
            }
        })
    })

    checkbox.addEventListener('change', function() {
        this.checked?dark_mode():light_mode();
    });

    // exception modal function start
    let error_msg = $('#error_Msg').html();
    if(error_msg!==""){
        $('#exception_modal').modal("show");
    }
    // exception modal function end

    // warning modal start
    let warningMsg = $("#warning_Msg").html();
    if(warningMsg!=="") {
        $('#warning_modal').modal("show");
    }
    // warning modal end

    // __sort by__ method
    !localStorage.getItem('ascending_sorting')?localStorage.setItem('ascending_sorting','true'):null;
    let ascending = localStorage.getItem('ascending_sorting');

    !localStorage.getItem('sortByDate')?localStorage.setItem('sortByDate','f'):null;

    const arr = [  // the table header titles' tags
        $('#identityNumber_colTitle'),
        $('#fullName_colTitle'),
        $('#phoneNumber_colTitle'),
        $('#salary_colTitle'),
        $('#sortByDate')
    ]

    $(arr).each(function() {
        $(this).on('click', function(e) {
            const str = $(this).attr('sortBy')?$(this).attr('sortBy'):"databaseId";
            let href = "users?sortBy="+str+"&&ascending="+ascending;
            localStorage.setItem('ascending_sorting',ascending==='true'?'false':'true');
            window.location.replace(href);
            if($(this).attr('sortBy')==="databaseId"){
                localStorage.setItem('sortByDate','t');
            }else{
                localStorage.setItem('sortByDate','f');
            }
        });
    });
})

if(localStorage.getItem('sortByDate')==="t"){
    $('#sortByDate').html(localStorage.getItem('ascending_sorting')==="true"?"Newest to oldest":"Oldest to newest");
    $('#sorted_As').html('sorted as : ');
}

const save_form = document.querySelector('#save_form');
const update_form = document.querySelector('#update_form');


submit_form =(form)=>{
    form.addEventListener('submit',event=>{
        // stop form submission
        event.preventDefault();
        const identityNumberElement = form.elements[name='identityNumber'];
        const fullNameElement = form.elements[name='fullName'];
        const phoneNumberElement = form.elements[name='phoneNumber'];
        const salaryElement = form.elements[name='salary'];

        // validate the form
        let fullNameValid = validateFullName(fullNameElement,FULL_NAME_REQUIRED,FULL_NAME_INVALID);
        let identityNumberValid = validateIdentityNumber(identityNumberElement,IDENTITY_NUMBER_REQUIRED,IDENTITY_NUMBER_INVALID);
        let phoneNumberValid = validatePhoneNumber(phoneNumberElement,PHONE_NUMBER_REQUIRED,PHONE_NUMBER_INVALID);
        let salaryValid = validateSalary(salaryElement,SALARY_INVALID);

        if(fullNameValid && identityNumberValid && phoneNumberValid && salaryValid){
            if(salaryElement.value==="")
                salaryElement.value=0;
            form.submit();
        }
    })
}
submit_form(update_form);
submit_form(save_form);


// show a message with a type of the input
function showMessage(input, message, type) {
    // get the <small> element and set the message
    const msg = input.parentNode.querySelector("small");
    msg.innerText = message;


    return type;
}
function showError(input, message) {
    return showMessage(input, message, false);
}

function showSuccess(input) {
    return showMessage(input, "", true);
}
function hasValue(input, message) {
    if (input.value.trim() === "") {
        return showError(input, message);
    }
    return showSuccess(input);
}
const IDENTITY_NUMBER_REQUIRED = "Please enter an identity number";
const FULL_NAME_REQUIRED = "Please enter a name";
const PHONE_NUMBER_REQUIRED = "Please enter a phone number";
const SALARY_REQUIRED = "Please enter a salary";

const IDENTITY_NUMBER_INVALID = "identity number must have 11 digit in it ,not start with zero ,and accept only digital value";
const FULL_NAME_INVALID = "full name must contain at least one character";
const PHONE_NUMBER_INVALID = "phone number must have 10 digit in it ,not start with zero ,and accept only digital value";
const SALARY_INVALID = "accept only digital value";

function validateIdentityNumber(input, requiredMsg, invalidMsg) {
    // check if the value is not empty
    if (!hasValue(input, requiredMsg)) {
        return false;
    }
    if(!input.value.match(/^(?!(0))\d{11}$/))
        return showError(input, invalidMsg);

    return true;
}
function validateFullName(input, requiredMsg, invalidMsg) {
    // check if the value is not empty
    if (!hasValue(input, requiredMsg)) {
        return false;
    }
    if(input.value.trim()==="")
        return showError(input, invalidMsg);

    return true;
}
function validatePhoneNumber(input, requiredMsg, invalidMsg) {
    // check if the value is not empty
    if (!hasValue(input, requiredMsg)) {
        return false;
    }
    if(!input.value.match(/^(?!(0))\d{10}$/))
        return showError(input, invalidMsg);

    return true;
}
function validateSalary(input, invalidMsg) {
    // if the value is empty return 0 no need to check

    if(!(input.value.match(/^\d+$/) || input.value===""))
        return showError(input, invalidMsg);

    return true;
}
