<?php

namespace OffreFideliteBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('OffreFideliteBundle:Default:index.html.twig');
    }
}
