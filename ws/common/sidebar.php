<div id="left">
            <div class="media user-media bg-dark dker">
                <div class="user-media-toggleHover">
                    <span class="fa fa-user"></span>
                </div>
                <div class="user-wrapper bg-dark">
                    <a class="user-link" href="">
                        <img class="media-object img-thumbnail user-img rounded-circle admin_img3" alt="User Picture"
                             src="img/admin.jpg">
                        <p class="text-white user-info">Welcome <?php echo $_SESSION['name']; ?></p>
                    </a>
                    <div class="search_bar col-lg-12">
                        <div class="input-group">
                            <input type="search" class="form-control" placeholder="search">
                            <span class="input-group-btn">
<button class="btn without_border" type="button"><span class="fa fa-search">
</span></button>
</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #menu -->
            <ul id="menu" class="bg-blue dker">
                <li >
                    <a href="index.html">
                        <i class="fa fa-home"></i>
                        <span class="link-title">&nbsp;Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="fa fa-th-large"></i>
                        <span class="link-title">&nbsp; Category</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul>
                        <li>
                            <a href="addcategory.php">
                                <i class="fa fa-angle-right"></i>
                                &nbsp; Add Category
                            </a>
                        </li>
                        <li>
                            <a href="managecategory.php">
                                <i class="fa fa-angle-right"></i>
                                &nbsp; Manage Category
                            </a>
                        </li>

                    </ul>
                </li>
                 <li>
                    <a href="javascript:;">
                        <i class="fa fa-th-large"></i>
                        <span class="link-title">&nbsp; Subcategory</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul>
                        <li>
                            <a href="addsubcategory.php">
                                <i class="fa fa-angle-right"></i>
                                &nbsp; Add Subcategory
                            </a>
                        </li>
                        <li>
                            <a href="managesubcategory.php">
                                <i class="fa fa-angle-right"></i>
                                &nbsp; Manage Subcategory
                            </a>
                        </li>

                    </ul>
                </li>

                <li>
                    <a href="calendar.html">
                        <i class="fa fa-calendar"></i>
                        <span class="link-title">&nbsp; Calendar</span>
                        <span class="tag tag-pill tag-primary float-xs-right calendar_tag">7</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-map-marker"></i>
                        <span class="link-title">&nbsp; Maps</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul>
                        <li>
                            <a href="maps.html">
                                <i class="fa fa-angle-right"></i> &nbsp; Google Maps
                            </a>
                        </li>
                        <li>
                            <a href="jqvmaps.html">
                                <i class="fa fa-angle-right"></i> &nbsp; Vector Maps
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /#menu -->
        </div>