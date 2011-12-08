<div class="sidebar">

    <?php if (!dynamic_sidebar('sidebar-widget-area')) : ?>
<!--        <div class="widget">
            <h3><?php// _e('Páginas', 'blue_with_grey'); ?></h3>
            <ul>
                <?php// wp_list_pages('title_li='); ?>
            </ul>
        </div>-->
        <div class="widget">
            <h3><?php _e('Informações', 'blue_with_grey'); ?></h3>
            <ul>
                <?php wp_list_categories('title_li='); ?>
            </ul>
        </div>
<!--        <div class="widget">
            <h3><?php //_e('Arquivos', 'blue_with_grey'); ?></h3>
            <ul>
                <?php// wp_get_archives('type=monthly'); ?>
            </ul>
        </div>-->
        <div class="widget">
            <h3><?php _e('Nuvem de termos','blue_with_grey')?></h3>
            <div id="tcdiv"></div>
            <script type="text/javascript">
              google.load("visualization", "1");
              google.setOnLoadCallback(draw);
              function draw() {
                data = new google.visualization.DataTable();
                data.addColumn('string', 'Label');
                data.addColumn('number', 'Value');
                data.addColumn('string', 'Link');
                data.addRows([
                    ['termo',980001,'http://www.globo.com'],
                    ['terminho',235566,'http://www.google.com']
                ]);
                var outputDiv = document.getElementById('tcdiv');
                var tc = new TermCloud(outputDiv);
                tc.draw(data, null);
              }
            </script>
        </div>
        <?php /* If this is the frontpage  if ( is_home() || is_page() ) { */ ?>
        <!--			<div class="widget">
                                        <h3><?php // _e( 'Meta', 'blue_with_grey' );  ?></h3>
                                        <ul>
        <?php //wp_register();  ?>
                                                <li><?php //wp_loginout(); ?></li>
        <?php //wp_meta();  ?>
                                        </ul>
                                </div>-->

        <?php //}  ?>
    <?php endif; ?>

</div>