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
        console.log("worked !")
        const href = $(this).attr("href");
        $.get(href,function(u){
            console.log(u.identityNumber);
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
    let exception_detector_oldState=false;
    let exception_detector = Boolean($("#error_sensor").html());
    let error_msg = $('#error_Msg').html();

    if(exception_detector!==exception_detector_oldState && error_msg!==""){
        exception_detector_oldState=exception_detector;
        $('#exception_modal').modal("show");
    }
    // exception modal function end
})