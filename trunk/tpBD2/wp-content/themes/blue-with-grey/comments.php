
<?php
/* This variable is for alternating comment background */
$oddcomment = 'class="alt" ';
?>

<!-- You can start editing here. -->
<div class="comments">
    <?php if ($comments) : ?>
        <h3><?php comments_number(__('Sem Respostas', 'blue_with_grey'), __('Uma Resposta', 'blue_with_grey'), '% ' . __('Respostas', 'blue_with_grey')); ?> to &#8220;<?php the_title(); ?>&#8221;</h3>
        <ul class="commentlist">
            <?php wp_list_comments(); ?>
        </ul>
    <?php else : // this is displayed if there are no comments so far ?>
        <?php if (comments_open()) : ?>
            <!-- If comments are open, but there are no comments. -->
        <?php else : // comments are closed ?>
            <!-- If comments are closed. -->
        <?php endif; ?>
    <?php endif; ?>
    <?php if (comments_open()) : ?><?php comment_form(); ?><?php endif; // if you delete this the sky will fall on your head ?>
</div>