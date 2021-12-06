$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#purchase', function () {
        console.log("testing");
    });

    $('body').on('click', '.delete-title', function () {
        fetch("user/dismiss-new-release", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.saved-book-card').remove();
        })
        console.log($(this).data('book-id'))
    })

    $('body').on('click', '.dismiss-new-release', function () {
        fetch("user/dismiss-new-release", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.new-release-card').remove();
        })
        console.log($(this).data('book-id'))
    })

    $('body').on('click', '.save-book', function () {
        fetch("user/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.new-release-card').remove();
        })
        console.log($(this).data('book-id'))
    })

    $('body').on('click', '.mark-purchased', function () {
        fetch("user/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.new-release-card').remove();
        })
        console.log($(this).data('book-id'))
    })

    $('body').on('click', '#demoButton', function () {
        fetch("/fake-new-releases", {method: "POST"})
            .then(()=> {
            window.location.reload();
        })
    })




})