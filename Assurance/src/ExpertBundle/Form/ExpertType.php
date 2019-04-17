<?php

namespace ExpertBundle\Form;


use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ExpertType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('cinex', TextType::class)->add('faxex',TextType::class)->add('nomex',TextType::class)->add('prenomex',TextType::class)->add('mailex',EmailType::class)->add('numeroex',NumberType::class)->add('adresseex',TextType::class)
            ->add('etatex',ChoiceType::class,['choices'=>
            [
                'non','oui',
            ],])->add('descriptionex',TextType::class)->add('imageFile',FileType::class, [
                'required' =>false])->add('save',SubmitType::class);
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ExpertBundle\Entity\Expert'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'expertbundle_expert';
    }


}
