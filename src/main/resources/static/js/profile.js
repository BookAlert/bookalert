$(() => {

    ///=================  FETCH DATA FOR AUTHOR
    $('body').on('click', '#purchase', function () {
        console.log("testing");
    });

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
    //     const text = $('#authorSearchInput').val();
    //     var url = new URL('author-suggestions', window.location.origin)
    //
    //     url.search = new URLSearchParams({search: text}).toString();
    //
    //     fetch(url)
    //         .then(response => response.json())
    //         .then(buildSearchResults)



})