<?php

namespace ContratBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ContratBundle:Default:index.html.twig');
    }
}
