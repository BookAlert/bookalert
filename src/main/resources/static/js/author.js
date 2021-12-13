$(() => {
    $('body').on('click', '.save-book', function () {
        fetch("/user/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        })
    })

    $('body').on('click', '.mark-purchased', function () {
        fetch("/user/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        })
    })

    $('body').on('click', '')

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
})