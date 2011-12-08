<?php get_header(); ?>

<div class="main">
    <?php if (have_posts()) : ?>
        <?php while (have_posts()) : the_post(); ?>
            <!-- Start: Post -->
            <div <?php post_class(); ?>>
                <h2 class="post-title"><a href="<?php the_permalink() ?>" rel="bookmark" title="Permanent Link to <?php the_title_attribute(); ?>"><?php the_title(); ?></a> <?php edit_post_link(__('Editar', 'blue_with_grey'), '', ''); ?></h2>
                <?php the_post_thumbnail(); ?>
                <?php the_excerpt(); ?>
                <p class="more"><a href="<?php the_permalink() ?>"><?php _e(' ', 'blue_with_grey'); ?> </a></p>
                <?php if (has_tag()): ?><p class="tags"><span><?php the_tags(""); ?></span></p><?php endif; ?>
                <div class="clear"><!-- --></div>
            </div>
            <!-- End: Post -->
        <?php endwhile; ?>

        <p class="pagination">
            <span class="prev"><?php next_posts_link(__('Posts Antigos', 'blue_with_grey')) ?></span>
            <span class="next"><?php previous_posts_link(__('Próximos Posts', 'blue_with_grey')) ?></span>
        </p>
    <?php else : ?>
        <h2 class="center"><?php _e('Página não encontrada.', 'blue_with_grey'); ?></h2>
        <p class="center"><?php _e('Desculpe-nos, mas estamos procurando por algo que não está aqui.', 'blue_with_grey'); ?></p>
        <?php get_search_form(); ?>
    <?php endif; ?>
</div>
<?php get_sidebar(); ?>
<?php get_footer(); ?>
