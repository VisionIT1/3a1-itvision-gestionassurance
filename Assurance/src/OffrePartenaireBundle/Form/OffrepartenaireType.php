<?php

namespace OffrePartenaireBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use MarqueBundle\Entity\Marque;

class OffrepartenaireType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('liboffre',TextType::class,array('required' => false,'label'=>'Libelle:'))->add('datedebutoffre',DateType::class,array('label'=>'Date debut:'))->add('datefinoffre',DateType::class,array('label'=>'Date Fin:'))->add('pourcentagereduction',TextType::class,array('required' => false,'label'=>'Pourcentage de réduction:'))->add('imgOff',TextType::class,array('required' => false,'label'=>'Image:'))->add('partenaire',EntityType::class,array(
            //query choices from this entity
            'class'=>'MarqueBundle:Marque',
            // utiliser le libelle comme propriété visible
            'choice_label'=>'libelle',
            //used to render a select box
            'multiple'=>false,'label'=>'Partenaire'
        ))->add('descripoffre',TextareaType::class, [
            'attr' => ['class' => 'tinymce'],
        ],array('required' => false,'label'=>'Description:'))->add('save',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'OffrePartenaireBundle\Entity\Offrepartenaire'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'offrepartenairebundle_offrepartenaire';
    }


}
