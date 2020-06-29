<#import "templates.ftl" as t>
<@t.page>
    <link rel="stylesheet" href="css/twentytwenty.css">

    <div class="container p-0 mt-5">
        <div class="row">
            <div class="m-0 col-4">
                <div class="twentytwenty-container mt-3">
                    <img src="img/after.jpg"/>
                    <img src="img/before.jpg"/>
                </div>
                <div class="twentytwenty-container mt-3">
                    <img src="img/after.jpg"/>
                    <img src="img/before.jpg"/>
                </div>
                <div class="twentytwenty-container mt-3">
                    <img src="img/after.jpg"/>
                    <img src="img/before.jpg"/>
                </div>
            </div>
            <div class="m-0 col-4">
                <div class="twentytwenty-container mt-3">
                    <img src="img/mbuntu-2.jpg"/>
                    <img src="img/CATALINA-02.jpeg"/>
                </div>
                <div class="twentytwenty-container mt-3">
                    <img src="img/before-3.jpg"/>
                    <img src="img/after-3.jpg"/>
                </div>
            </div>
            <div class="m-0 col-4">
                <div class="twentytwenty-container mt-3">
                    <img src="img/before-2.jpg"/>
                    <img src="img/after-2.jpg"/>
                </div>
                <div class="twentytwenty-container mt-3">
                    <img src="img/after.jpg"/>
                    <img src="img/before.jpg"/>
                </div>
                <div class="twentytwenty-container mt-3">
                    <img src="img/after.jpg"/>
                    <img src="img/before.jpg"/>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery.event.move.js"></script>
    <script src="js/jquery.twentytwenty.js"></script>
    <script>
        $(document).ready(function () {
            $(".twentytwenty-container").twentytwenty();
        });
        $(".retouchul").addClass("active");
    </script>
</@t.page>