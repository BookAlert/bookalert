$(() => {

    loadSavedBooks();
    loadNewReleases();
    loadUpcomingReleases();

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#purchase', function () {
        console.log("testing");
    });


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
        fetch("user/save-book", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            loadSavedBooks();
            loadNewReleases();
            loadUpcomingReleases();
        })
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
            loadSavedBooks();
            loadNewReleases();
            loadUpcomingReleases();
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
                $('[data-toggle="tooltip"]').tooltip({
                    })
            })
    }

    function loadNewReleases() {
        fetch("/profile/new-releases")
            .then((res) => res.text())
            .then((res) => {
                $('#profileNewReleases').html(res)
                $('[data-toggle="tooltip"]').tooltip()
            })
    }

    function loadUpcomingReleases() {
        fetch("/profile/upcoming-releases")
            .then((res) => res.text())
            .then((res) => {
                $('#profileUpcomingReleases').html(res)
                $('[data-toggle="tooltip"]').tooltip()
            })
    }


})