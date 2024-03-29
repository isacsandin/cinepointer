<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" <?php language_attributes('xhtml'); ?>>
    <head profile="http://gmpg.org/xfn/11">
        <link rel="stylesheet" type="text/css" href="http://visapi-gadgets.googlecode.com/svn/trunk/termcloud/tc.css"/>
        <script type="text/javascript" src="http://visapi-gadgets.googlecode.com/svn/trunk/termcloud/tc.js"></script>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <script type="text/javascript" src="jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="googleChart.js"></script>

        <meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
        <title><?php
/*
 * Print the <title> tag based on what is being viewed.
 */
global $page, $paged;

wp_title('|', true, 'right');

// Add the blog name.
bloginfo('name');

// Add the blog description for the home/front page.
$site_description = get_bloginfo('description', 'display');
if ($site_description && ( is_home() || is_front_page() ))
    echo " | $site_description";

// Add a page number if necessary:
if ($paged >= 2 || $page >= 2)
    echo ' | ' . sprintf(__('Pá %s', 'blue_with_grey'), max($paged, $page));
?></title>


        <link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
        <?php if (is_singular() && get_option('thread_comments'))
            wp_enqueue_script('comment-reply'); ?>
        <link rel="stylesheet" href="<?php echo get_stylesheet_uri(); ?>" type="text/css" />
        <?php wp_head(); ?>

    </head>
    <body <?php body_class(); ?>>
        <div class="root">
            <div class="header">
                <?php get_search_form(); ?>
                <p class="logo"><a href="<?php echo home_url(); ?>/" name="top"><?php bloginfo('name'); ?></a></p>
                <p class="tagline"><?php bloginfo('description'); ?></p>
            </div>
            <div class="nav">
                <div>
                    <?php wp_nav_menu(array('fallback_cb' => 'blue_with_grey_page_menu', 'menu' => 'primary', 'depth' => '3', 'theme_location' => 'primary', 'link_before' => '', 'link_after' => '', 'container' => false)); ?>
                    <div class="clear"><!-- --></div>
                </div>
            </div>

            <div class="content">