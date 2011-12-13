<div class="sidebar">

    <?php if (!dynamic_sidebar('sidebar-widget-area')) : ?>
<!--        <div class="widget">
            <h3><?php// _e('Páginas', 'blue_with_grey'); ?></h3>
            <ul>
                <?php// wp_list_pages('title_li='); ?>
            </ul>
        </div>-->
        <div class="widget">
            <h3><?php _e('Nuvem de termos','blue_with_grey')?></h3>
            <div id="div_tags"></div>
                <!--Load the AJAX API-->
        </div>
        <div class="widget">
            <h3><?php _e('Nuvem de Hashtags','blue_with_grey')?></h3>
            <div id="div_hashtags"></div>
                <!--Load the AJAX API-->
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