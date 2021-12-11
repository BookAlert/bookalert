$(() => {



    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#purchase', function () {
        console.log("testing");
    });


    // /// Carousel Handler


    // $('.slider-for').slick({
    //     slidesToShow: 1,
    //     slidesToScroll: 1,
    //     arrows: false,
    //     fade: true,
    //     asNavFor: '.slider-nav'
    // });
    // $('.slider-nav').slick({
    //     slidesToShow: 4,
    //     slidesToScroll: 1,
    //     asNavFor: '.slider-for',
    //     dots: true,
    //     centerMode: true,
    //     focusOnSelect: true
    // });


    /// ================== SAVE UPCOMING-RELEASE METHOD(UserController)
    $('body').on('click', '.save-upcoming-book',  function (event) {
        event.preventDefault();
         fetch("user/save-upcoming-title", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then( () => {
            $(this).closest('.upcoming-book-card').remove();
            location.reload()
        })
        console.log($(this).data('book-id'))

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
            $(this).closest('.new-release-card').remove();
        })
    })

    //============== DISMISS UPCOMING RELEASE
    $('body').on('click', '.dismiss-upcoming-release', function () {
        fetch("user/dismiss-upcoming-release", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.upcoming-book-card').remove();
        })
    })

    //================= MARK PURCHASED UPCOMING
    $('body').on('click', '.mark-upcoming', function () {
        fetch("user/purchased-upcoming", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            $(this).closest('.new-release-card').remove();
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
            $(this).closest('.new-release-card').remove();
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
            body: JSON.stringify({id: $(this).data('book-id') })
        }).then(()=> {
            window.location.reload()
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
            $(this).closest('.saved-book-card').remove();
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
            $(this).closest('.new-release-card').remove();
            // window.location.reload();
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
            $(this).closest('.new-release-card').remove();
        })
    })

    $('body').on('click', '#demoButton', function () {
        fetch("/fake-new-releases", {method: "POST"})
            .then(()=> {
            window.location.reload();
        })
    })




})