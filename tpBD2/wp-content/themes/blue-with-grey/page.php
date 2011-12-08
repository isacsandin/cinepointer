<?php get_header(); ?>
<div class="main">
    <div class="post">
        <?php if (have_posts())
            while (have_posts()) : the_post(); ?>
                <h1 class="post-title"><?php the_title(); ?> <?php edit_post_link(__('Editar', 'blue_with_grey'), '', ''); ?></h1>
                <?php the_content(); ?>
    <?php endwhile; ?>
    </div>
</div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>