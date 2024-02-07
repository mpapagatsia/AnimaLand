INSERT INTO public.animal (id,"name",species,"uuid") VALUES
	 (1,'Blue','cat','id1'),
	 (2,'Romeo','dog','id2'),
	 (3,'Jenny','dog','id3'),
	 (4,'Nemo','fish','id4');

INSERT INTO public.trick ("name",species) VALUES
	 ('walksOnLaptop','cat'),
	 ('jumps','dog'),
	 ('rollsOver','dog'),
	 ('barks','dog');


INSERT INTO public.animal_tricks (animal_id,trick_id) VALUES
	 (1,1),
	 (2,2),
	 (2,3),
	 (2,4),
	 (3,2),
	 (3,4);
