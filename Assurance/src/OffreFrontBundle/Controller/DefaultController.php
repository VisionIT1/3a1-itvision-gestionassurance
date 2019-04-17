<?php

namespace OffreFrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('OffreFrontBundle:Default:index.html.twig');
    }
}
