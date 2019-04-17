<?php

namespace AffectationOffreBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AffectationOffreBundle:Default:index.html.twig');
    }
}
