//This is for blocking the contextmenu. Does not work < IE 9, but who uses that anyways.
document.addEventListener('contextmenu', function (e) {
    e.preventDefault();
}, false);

function Post(x, y) {
    //This creates a form with ajax.
    var form = $('<form></form>');

    //Setting the post and controller action.
    form.attr("method", "post");
    Post.firstClick = 0;
    
    form.attr("action", "right");

    //This adds the hidden input fields that hold the coordinates of the cell.
    $(form).append("<input type='hidden' name='xcoor' value='" + x + "' />");
    $(form).append("<input type='hidden' name='ycoor' value='" + y + "' />");

    //This is required because it attaches this invisible form to the view.
    $(document.body).append(form);
    form.submit();
}
