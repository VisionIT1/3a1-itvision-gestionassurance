<?php

namespace OffrePartenaireBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('OffrePartenaireBundle:Default:index.html.twig');
    }
}
