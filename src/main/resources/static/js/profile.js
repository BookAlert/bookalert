$(() => {

    loadSavedBooks();
    loadNewReleases();
    loadUpcomingReleases();

    //============== DISMISS NEW RELEASE
    $('body').on('click', '.dismiss-new-release', function () {
        fetch("user/dismiss-new-release", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            loadNewReleases();
            loadUpcomingReleases();
        })
    })

//============== DISMISS NEW RELEASE
    $('body').on('click', '.dismiss-new-release', function () {
        fetch("user/dismiss-new-release", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            loadNewReleases();
        })
        console.log($(this).data('book-id'))
    })

//============== DISMISS ALL NEW RELEASE
    $('body').on('click', '.dismiss-all', function () {
        fetch("user/dismiss-all-new-releases", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
        }).then(()=> {
            loadNewReleases();
        })
    })


    /// ================== DELETE FROM USER TITLE LIST METHOD(UserController)
    $('body').on('click', '.delete-title', function () {
        fetch("user/delete-title", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            loadSavedBooks();
        })
        console.log($(this).data('book-id'))
    })




/// ====================== SAVE NEW RELEASE
    $('body').on('click', '.save-book', function () {
        $(this).tooltip('hide')
        fetch("user/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {

            iziToast.success({
                title: 'Success',
                message: 'Successfully saved book!',
                position: 'topCenter',
                timeout: 1500
            });
            loadSavedBooks();
            loadNewReleases();
            loadUpcomingReleases();
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
        const bookId = $(this).data('book-id');
        $(this).tooltip('hide')
        fetch("user/mark-purchased", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: bookId })
        }).then(()=> {
            loadSavedBooks();
            loadNewReleases();
            loadUpcomingReleases();
            iziToast.info({
                title: 'Purchased',
                message: 'You own this book',
                position: 'topCenter',
                timeout: 1500
            });

        }).catch(() => {
            iziToast.fail({
                title: 'Failure',
                message: 'Book exists!',
                position: 'topCenter',
                timeout: 1500
            })
        })
    })

    $('body').on('click', '#demoButton', function () {
        fetch("/fake-new-releases", {method: "POST"})
            .then(()=> {
                loadNewReleases();
                loadUpcomingReleases();
        })
    })
    function loadSavedBooks() {
        fetch("/profile/saved-books")
            .then((res) => res.text())
            .then((res) => {
                $('#profileSavedBooks').html(res)
                $('[data-toggle="tooltip"]').tooltip({ trigger: 'hover' })
            })
    }

    function loadNewReleases() {
        fetch("/profile/new-releases")
            .then((res) => res.text())
            .then((res) => {
                $('#profileNewReleases').html(res)
                $('[data-toggle="tooltip"]').tooltip({ trigger: 'hover' })
            })
    }

    function loadUpcomingReleases() {
        fetch("/profile/upcoming-releases")
            .then((res) => res.text())
            .then((res) => {
                $('#profileUpcomingReleases').html(res)
                $('[data-toggle="tooltip"]').tooltip({ trigger: 'hover' })
            })
    }


})