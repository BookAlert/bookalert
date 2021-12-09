$(() => {
    $('body').on('click', '.save-book', function () {
        fetch("/authors/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        })
    })
    console.log($(this).data('book-id'))

    $('body').on('click', '.mark-purchased', function () {
        fetch("/authors/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        })
    })
    console.log($(this).data('book-id'))
})