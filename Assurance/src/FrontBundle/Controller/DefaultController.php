<?php

namespace FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('FrontBundle:Default:index.html.twig');
    }

    public function serviceAction()
    {
        return $this->render('FrontBundle:Default:insurance.html.twig');
    }

    public function ressourceAction()
    {
        return $this->render('FrontBundle:Default:ressource.html.twig');
    }

    public function contactAction()
    {
        return $this->render('FrontBundle:Default:contact.html.twig');
    }

    public function aboutAction()
    {
        return $this->render('FrontBundle:Default:about.html.twig');
    }


}
