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
                <!--Load the AJAX API-->

    <script type="text/javascript">
    
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});
      
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
      
    function drawChart() {
      var jsonData = $.ajax({
          url: "getDataTerms.php",
          dataType:"json",
          async: false
          }).responseText;
          
      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(jsonData);

      // Instantiate and draw our chart, passing in some options.
    var outputDiv = document.getElementById('tcdiv');
    var tc = new TermCloud(outputDiv);
    tc.draw(data, null);
    }

    </script>
        </div>
<div>
    <script src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'search',
  search: 'corrupção',
  interval: 30000,
  title: 'Tweets em tempo real...',
  subject: '',
  width: 298,
  height: 300,
  theme: {
    shell: {
      background: '#eee',
      color: '#b30d0d'
    },
    tweets: {
      background: '#ffffff',
      color: '#000000',
      links: '#1985b5'
    }
  },
  features: {
    scrollbar: false,
    loop: true,
    live: true,
    behavior: 'default'
  }
}).render().start();
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