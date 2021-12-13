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
            $(this).closest('.author-book-card').find('.saved-text').toggleClass('d-none').toggleClass('d-block');
            iziToast.success({
                title: 'Success',
                message: 'Successfully saved book!',
                position: 'topRight',
                timeout: 1500
            });
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Book exists!',
                position: 'center',
                timeout: 1500
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
            $(this).closest('.author-book-card').find('.purchased-text').toggleClass('d-none').toggleClass('d-block');
            iziToast.info({
                title: 'Purchased',
                message: 'You own this book',
                position: 'topLeft',
                timeout: 1500
            });
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Failed',
                position: 'center',
                timeout: 1500
            })
        })
    })
    // console.log($(this).data('book-id'))

    // $('body').on('click', '')

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })


})