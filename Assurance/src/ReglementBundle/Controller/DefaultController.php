<?php

namespace ReglementBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ReglementBundle:Default:index.html.twig');
    }
}
