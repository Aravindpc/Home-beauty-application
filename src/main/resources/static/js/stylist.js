   let sidebar = document.querySelector(".sidebar");
                let sidebarBtn = document.querySelector(".sidebarBtn");
                sidebarBtn.onclick = function () {
                    sidebar.classList.toggle("active");
                    if (sidebar.classList.contains("active")) {
                        sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
                    } else
                        sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
                }
                function editdetails2() {
                }

                var close = document.getElementsByClassName("closebtn");
                var i;

                for (i = 0; i < close.length; i++) {
                    close[i].onclick = function () {
                        var div = this.parentElement;
                        div.style.opacity = "0";
                        setTimeout(function () { div.style.display = "none"; }, 600);
                    }
                }
                $(function () {
                    $('nav ul li a').each(function () {
                        var isActive = this.pathname === location.pathname;
                        $(this).parent().toggleClass('active', isActive);
                    });
});