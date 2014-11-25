function reloadMessage() {
    $.get('../messageboard/messages/all', function(data) {
        $("#board").html(data);
    });
}

reloadMessage();
$(function() {
    $('#form').submit(function(event){
        if(!this.checkValidity())
        {
            $(this).find(':input').each(function() {
                if(!this.validity.valid) {
                    $('#alert').html('<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><strong>Error! </strong>please enter corrrect message.</div>');
                    $(this).focus();
                    return false;
                }
            });
            event.preventDefault();
            return false;
        }
        $.ajax({
            type: "POST",
            url: '../messageboard/messages',
            data: {
                "name":window.form.name.value,
                "email":window.form.email.value,
                "content":window.form.content.value
            },
            success: function() {
                $('#success').html('<div class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><strong>Success!</strong> Thank you for leaving message.</div>');
                window.form.reset();
                reloadMessage();
                $('#leaveMessage').modal('hide');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('#alert').html('<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><strong>Error! </strong>type: "' + textStatus + '", message: "' + errorThrown + '".</div>');
                reloadMessage();
            }
        });
        return false;
    });
});