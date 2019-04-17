<?php

namespace ExpertBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ExpertBundle:Default:index.html.twig');
    }
}
