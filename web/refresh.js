/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function(){
    setInterval(function(){
        $.get('/sudoku/poll', function(data){
                $("#result").html(data)
            }
        )
        //$("#view").set
    },200);

});
