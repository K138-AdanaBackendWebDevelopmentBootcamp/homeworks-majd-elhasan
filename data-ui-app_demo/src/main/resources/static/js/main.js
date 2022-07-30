
$(document).ready(function (){
    $(".table .edit-btn").on("click",function (event){
        $('#edit_Modal').modal('show');
        event.preventDefault();
        console.log("worked !")
        const href = $(this).attr("href");
        $.get(href,function(n,status){
            $('#nameEdit').val(n.name);
            $('#capitalEdit').val(n.capital);
            $('#updatedByEdit').val(n.updatedBy);
            $('#idEdit').val(n.id);
        })

    })

    $('.table  #delete-btn').on('click',function (event){
        event.preventDefault();
        const href =$(this).attr('href');
        $('#delete_modal #confirm-del').attr('href',href);
        $('#delete_modal').modal('show');
    })
})
/*

 */