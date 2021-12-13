$(() => {
    $('body').on('click', '.save-book', function () {
        fetch("/user/save-book", {
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
        fetch("/user/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id')})
        }).then(() => {
            $('#purchased-text').css('visibility', 'visible');
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
    })
    console.log($(this).data('book-id'))

    $('body').on('click', '')

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })


})