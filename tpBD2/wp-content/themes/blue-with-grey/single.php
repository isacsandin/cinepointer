<?php get_header(); ?>
<div class="main">
	<div class="post">
	<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
		<h1 class="post-title"><?php the_title(); ?> <?php edit_post_link(__('Editar', 'blog09'), '', ''); ?></h1>
		<?php the_content(); ?>
		<?php wp_link_pages(array('before' => '<p class="pages"><strong>'.__('Pages', 'blog09').':</strong> ', 'after' => '</p>', 'next_or_number' => 'number')); ?>
		<?php if(has_tag()): ?><p class="tags"><span><?php the_tags(""); ?></span></p><?php endif; ?>
		<p><?php posts_nav_link(); ?></p>
		<p class="pagination">
			<span class="prev"><?php previous_post_link('%link'); ?></span>
			<span class="next"><?php next_post_link('%link'); ?></span>
		</p>
	<?php endwhile; endif; ?>
	</div>
</div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>
