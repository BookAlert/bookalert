$(() => {
    //==========SAVE-BOOK HANDLER
    $('body').on('click', '.save-book', function () {
        fetch("/authors/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id')})
        }).then(() => {
            iziToast.success({
                title: 'Success',
                message: 'Successfully saved book!',
                position: 'topRight',
                timeout: 3000
            });
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Book exists!',
                position: 'center',
                timeout: 3000
            })
        })
    })
    // console.log($(this).data('book-id'))
    //==========MARK PURCHASED HANDLER
    $('body').on('click', '.mark-purchased', function () {
        fetch("/authors/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id')})
        }).then(() => {
            iziToast.info({
                title: 'Purchased',
                message: 'You own this book',
                position: 'topLeft',
                timeout: 5000
            });
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Failed',
                position: 'center',
                timeout: 3000
            })
        })
        // console.log($(this).data('book-id'))
    })

    $(function(){
        $("#purchase-button").on("click", function(){
            $("#purchased-text").css("visibility", "visible");
        });
    })

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
})