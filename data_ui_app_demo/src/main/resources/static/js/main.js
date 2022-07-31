
$(document).ready(function (){
    $(".table .edit-btn").on("click",function (event){
        $('#edit_Modal').modal('show');
        event.preventDefault();
        console.log("worked !")
        const href = $(this).attr("href");
        $.get(href,function(n){  // in the video I watched on YouTube there was another parameter beside to n ,  it was (n,status) so at this date 2022/08/1 I didn't know about that
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