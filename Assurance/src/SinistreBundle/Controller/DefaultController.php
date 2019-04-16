<?php

namespace SinistreBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('SinistreBundle:Default:index.html.twig');
    }
}
