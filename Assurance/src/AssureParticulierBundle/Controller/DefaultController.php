<?php

namespace AssureParticulierBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AssureParticulierBundle:Default:index.html.twig');
    }
}
