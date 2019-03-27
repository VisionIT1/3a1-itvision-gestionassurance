<?php

namespace AssureEntrepriseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('AssureEntrepriseBundle:Default:index.html.twig');
    }
}
