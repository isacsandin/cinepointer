<?php get_header(); ?>
<div class="main">
	<?php if (have_posts()) : ?>

		<?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
		<?php /* If this is a category archive */ if (is_category()) { ?>
		  <h1><?php single_cat_title(); ?></h1>
		<?php /* If this is a tag archive */ } elseif( is_tag() ) { ?>
		  <h1><?php single_tag_title(); ?></h1>
		<?php /* If this is a daily archive */ } elseif (is_day()) { ?>
		  <h1><?php echo get_the_time('F jS, Y'); ?></h1>
		<?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
		  <h1><?php echo get_the_time('F, Y'); ?></h1>
		<?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
		  <h1><?php echo get_the_time('Y'); ?></h1>
		<?php /* If this is an author archive */ } elseif (is_author()) { ?>
		  <h1><?php _e( 'Arquivos do Autor', 'blue_with_grey' ); ?></h1>
		<?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
		  <h1><?php _e( 'Arquivos do Blog', 'blue_with_grey' ); ?></h1>
		<?php } ?>

		<?php while (have_posts()) : the_post(); ?>
			<!-- Start: Post -->
			<div <?php post_class(); ?>>
				<h2 class="post-title"><a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title_attribute(); ?>"><?php the_title(); ?></a> <?php edit_post_link(__('Editar', 'blue_with_grey'), '', ''); ?></h2>
				
				<?php the_post_thumbnail(); ?>
				<?php the_excerpt(); ?>
				<p class="more"><a href="<?php the_permalink() ?>"><?php _e( ' ', 'blue_with_grey' );?> </a></p>
				<?php if(has_tag()): ?><p class="tags"><span><?php the_tags(""); ?></span></p><?php endif; ?>
				<div class="clear"><!-- --></div>
			</div>
			<!-- End: Post -->
		<?php endwhile; ?>

		<p class="pagination">
			<span class="prev"><?php next_posts_link(__('Posts Antigos', 'blue_with_grey')) ?></span>
			<span class="next"><?php previous_posts_link(__('Próximos Posts', 'blue_with_grey')) ?></span>
		</p>

	<?php else : ?>
		<h2><?php _e( 'Nada foi encontrado.', 'blue_with_grey' ); ?></h2>
		<p><?php _e( 'Desculpem-nos, mas nós estamos procurando algo que não existe.', 'blue_with_grey' ); ?></p>
		<?php get_search_form(); ?>
	<?php endif; ?>
</div>
<?php get_sidebar(); ?>

<?php get_footer(); ?>
