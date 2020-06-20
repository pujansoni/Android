<?php include"common/header.php"; ?>
    <!-- /#top -->
    <?php include"common/sidebar.php"; ?>
        <div id="content" class="bg-container">
            <header class="head">
                <div class="main-bar row">
                    <div class="col-xs-6">
                        <h4 class="m-t-5">
                            <i class="fa fa-home"></i>
                            Dashboard
                        </h4>
                    </div>
                </div>
            </header>
            <div class="outer">
                <div class="inner bg-container">
                    <div class="row">
                        <div class="col-xl-6 col-lg-7 col-xs-12">
                            <div class="row">
                                <div class="col-sm-6 col-xs-12">
                                    <div class="bg-primary top_cards">
                                        <div class="row icon_margin_left">

                                            <div class="col-lg-5 icon_padd_left">
                                                <div class="float-xs-left">
<span class="fa-stack fa-sm">
<i class="fa fa-circle fa-stack-2x"></i>
<i class="fa fa-shopping-cart fa-stack-1x fa-inverse text-primary sales_hover"></i>
</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-7 icon_padd_right">
                                                <div class="float-xs-right cards_content">
                                                    <span class="number_val" id="sales_count"></span><i
                                                        class="fa fa-long-arrow-up fa-2x"></i>
                                                    <br/>
                                                    <span class="card_description">Sales</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-xs-12">
                                    <div class="bg-success top_cards">
                                        <div class="row icon_margin_left">
                                            <div class="col-lg-5 icon_padd_left">
                                                <div class="float-xs-left">
<span class="fa-stack fa-sm">
<i class="fa fa-circle fa-stack-2x"></i>
<i class="fa fa-eye fa-stack-1x fa-inverse text-success visit_icon"></i>
</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-7 icon_padd_right">
                                                <div class="float-xs-right cards_content">
                                                    <span class="number_val" id="visitors_count"></span><i
                                                        class="fa fa-long-arrow-up fa-2x"></i>
                                                    <br/>
                                                    <span class="card_description">Visitors</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-xs-12">
                                    <div class="bg-warning top_cards">
                                        <div class="row icon_margin_left">
                                            <div class="col-lg-5 icon_padd_left">
                                                <div class="float-xs-left">
<span class="fa-stack fa-sm">
<i class="fa fa-circle fa-stack-2x"></i>
<i class="fa fa-usd fa-stack-1x fa-inverse text-warning revenue_icon"></i>
</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-7 icon_padd_right">
                                                <div class="float-xs-right cards_content">
                                                    <span class="number_val" id="revenue_count"></span><i
                                                        class="fa fa-long-arrow-up fa-2x"></i>
                                                    <br/>
                                                    <span class="card_description">Revenue</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-xs-12">
                                    <div class="bg-mint top_cards">
                                        <div class="row icon_margin_left">
                                            <div class="col-lg-5 icon_padd_left">
                                                <div class="float-xs-left">
<span class="fa-stack fa-sm">
<i class="fa fa-circle fa-stack-2x"></i>
<i class="fa fa-users  fa-stack-1x fa-inverse text-mint sub"></i>
</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-7 icon_padd_right">
                                                <div class="float-xs-right cards_content">
                                                    <span class="number_val" id="subscribers_count"></span><i
                                                        class="fa fa-long-arrow-down fa-2x"></i>
                                                    <br/>
                                                    <span class="card_description">Subscribers</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-5 col-xs-12 stat_align">
                            <div class="card weather_section md_align_section">
                                <div class="card-block">
                                    <div class="row margin_align">
                                        <div class="col-xs-12">
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <div class="icon sun-shower">
                                                        <div class="cloud"></div>
                                                        <div class="sun">
                                                            <div class="rays"></div>
                                                        </div>
                                                        <div class="rain"></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="weather-value">
<span class=" text-white"><span class="degree">25&deg;</span>
</span>
                                                    </div>
                                                    <div class="weather_location">
                                                        <span class="text-white"><i class="fa fa-map-marker"></i> London</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row weekly_report">
                                                <div class="col-xs-3">
                                                    <span>Mon</span>
                                                    <br/>
                                                    <img src="img/w1.png" alt="weather">
                                                    <p>27&deg;</p>
                                                </div>
                                                <div class="col-xs-3">
                                                    <span>Tue</span>
                                                    <br/>
                                                    <img src="img/w2.png" alt="weather">
                                                    <p>23&deg;</p>
                                                </div>
                                                <div class="col-xs-3">
                                                    <span>Wed</span>
                                                    <br/>
                                                    <img src="img/w3.png" alt="weather">
                                                    <p>19&deg;</p>
                                                </div>
                                                <div class="col-xs-3">
                                                    <span>Thu</span>
                                                    <br/>
                                                    <img src="img/w4.png" alt="weather">
                                                    <p>38&deg;</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <!-- /.inner -->
                </div>
                <!-- /.outer -->
            </div>
            <!-- /#content -->
        </div>
    </div>
    <!--wrapper-->
    <?php include "common/footer.php"; ?>