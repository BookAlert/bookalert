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
            $(this).closest('.author-book-card').find('.purchased-text').removeClass('d-block').addClass('d-none');
            $(this).closest('.author-book-card').find('.saved-text').removeClass('d-none').addClass('d-block');
            iziToast.success({
                title: 'Success',
                message: 'Successfully saved book!',
                position: 'topCenter',
                timeout: 1500
            });
            this.setAttribute('disabled', 'true')
            $(this).tooltip('hide')
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Book exists!',
                position: 'topCenter',
                timeout: 1500
            })
        })
    })

    $('body').on('click', '.mark-purchased', function () {
        fetch("/user/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id')})
        }).then(() => {
            $(this).closest('.author-book-card').find('.saved-text').removeClass('d-block').addClass('d-none');
            $(this).closest('.author-book-card').find('.purchased-text').removeClass('d-none').addClass('d-block');
            iziToast.info({
                title: 'Purchased',
                message: 'You own this book',
                position: 'topCenter',
                timeout: 1500
            });
            this.setAttribute('disabled', 'true')
            $(this).tooltip('hide')
        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Failed',
                position: 'topCenter',
                timeout: 1500
            })
        })
    })

    $('[data-toggle="tooltip"]').tooltip({
        trigger : 'hover'
    })


})